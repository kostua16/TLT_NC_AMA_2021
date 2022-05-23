package nc.unc.ama.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import nc.unc.ama.common.dto.ConfirmationTokenDTO;
import nc.unc.ama.common.dto.RestAuthProperties;
import nc.unc.ama.common.dto.StaffRegistrationDTO;
import nc.unc.ama.user.config.UsersApiConfig;
import nc.unc.ama.user.entities.ConfirmationToken;
import nc.unc.ama.user.entities.UserAuthority;
import nc.unc.ama.user.entities.UserEntity;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRegistrationDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UserStatus;
import nc.unc.ama.common.dto.UserUpdateDTO;
import nc.unc.ama.user.err.TokenExpiredException;
import nc.unc.ama.user.err.UserAlreadyExistsException;
import nc.unc.ama.user.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"PMD.TooManyMethods", "PMD.ShortVariable"})
public class UsersService implements UserDetailsService {


    private final UserRepo repo;

    private final PasswordEncoder passwordEncoder;

    private final RestAuthProperties apiUserProperties;

    private final ConfirmTokenService tokenService;

    private final UsersApiConfig apiConfig;

    @Autowired
    @SuppressWarnings("PMD")
    public UsersService(
        final UserRepo repo,
        final PasswordEncoder passwordEncoder,
        final RestAuthProperties properties,
        final ConfirmTokenService tokenService,
        final UsersApiConfig apiConfig
    ) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.apiUserProperties = properties;
        this.tokenService = tokenService;
        this.apiConfig = apiConfig;
    }

    @Transactional
    public ConfirmationTokenDTO register(final UserRegistrationDTO reg, final UserRoles role) {
        if (this.existsUser(reg.getEmail())) {
            throw new UserAlreadyExistsException(reg.getEmail());
        } else {
            final UserEntity forRegister = UserEntity.newUser(
                reg,
                role,
                this.passwordEncoder.encode(reg.getPassword())
            );
            final UserEntity user = this.repo.save(forRegister);
            return this.tokenService.generateToken(user).toInfo();
        }
    }

    @Transactional
    public ConfirmationTokenDTO registerStaff(final StaffRegistrationDTO reg, final UserRoles role) {
       ConfirmationTokenDTO tokenDTO =  this.register(reg, role);
       updateRating(tokenDTO.getUser().getId(), curr -> curr + 100);
       updateStaffType(tokenDTO.getUser().getId(), reg.getStaffTypeId());
       return tokenDTO;
    }
    @Transactional
    public boolean registerIfNotExists(final UserRegistrationDTO reg, final UserRoles role) {
        boolean result;
        if (this.existsUser(reg.getEmail())) {
            result = false;
        } else {
            try {
                this.register(reg, role);
                result = true;
            } catch (UserAlreadyExistsException exception) {
                result = false;
            }
        }
        return result;
    }

    @Transactional
    public UserInfoDTO updateUser(final UserUpdateDTO update) {
        final Optional<UserEntity> found = this.getUser(update.getId());
        if (found.isPresent()) {
            final UserEntity user = found.get();
            return this.repo.save(
                user.update(update, this.passwordEncoder.encode(update.getPassword()))
            ).toInfo();
        } else {
            throw new UsernameNotFoundException(update.getEmail());
        }
    }

    @Transactional
    public UserInfoDTO activateUserByToken(final String token) {
        final Optional<ConfirmationToken> foundToken = this.tokenService.getToken(token);
        if (foundToken.isPresent()) {
            final ConfirmationToken confirmationToken = foundToken.get();
            final LocalDateTime now = LocalDateTime.now();
            if (confirmationToken.getExpiresAt().isAfter(now)) {
                final UserEntity user = confirmationToken.getUser();
                confirmationToken.setConfirmedAt(now);
                user.setStatus(UserStatus.ACTIVE);
                this.tokenService.saveConfirmToken(confirmationToken);
                return this.repo.save(user).toInfo();
            } else {
                throw new TokenExpiredException("Token expired: " + token);
            }

        } else {
            throw new UsernameNotFoundException("Token: " + token);
        }
    }

    @Transactional
    public UserInfoDTO activateUserById(final UUID id) {
        final Optional<UserEntity> found = this.getUser(id);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStatus(UserStatus.ACTIVE);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(id.toString());
        }
    }

    @Transactional
    public UserInfoDTO activateUserByName(final String userName) {
        final Optional<UserEntity> found = this.findUser(userName);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStatus(UserStatus.ACTIVE);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(userName);
        }
    }

    @Transactional
    public UserInfoDTO activateUserByCode(final String code) {
        final Optional<UserEntity> found = this.repo.findById(UUID.fromString(code));
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStatus(UserStatus.ACTIVE);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(code);
        }
    }

    @Transactional
    public UserInfoDTO lockUser(final UUID id) {
        final Optional<UserEntity> found = this.getUser(id);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStatus(UserStatus.LOCKED);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(id.toString());
        }
    }

    @Transactional
    public UserInfoDTO expireUser(final UUID id) {
        final Optional<UserEntity> found = this.getUser(id);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStatus(UserStatus.EXPIRED);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(id.toString());
        }
    }

    @Transactional
    public UserInfoDTO updateRating(
        final UUID id,
        final IntUnaryOperator rating
    ) {
        final Optional<UserEntity> found = this.getUser(id);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setRating(rating.applyAsInt(user.getRating()));
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(id.toString());
        }
    }

    @Transactional
    public UserInfoDTO updateStaffType(final UUID id, final Long typeId){
        final Optional<UserEntity> found = this.getUser(id);
        if (found.isPresent()) {
            final UserEntity user = found.get();
            user.setStaffTypeId(typeId);
            return this.repo.save(user).toInfo();
        } else {
            throw new UsernameNotFoundException(id.toString());
        }
    }

    public boolean existsUser(final String username) {
        return this.findUser(username).isPresent();
    }

    public boolean existsUser(final UUID id) {
        return this.getUser(id).isPresent();
    }

    public Optional<UserInfoDTO> findUserDetails(final String username) {
        return this.findUser(username).map(UserEntity::toInfo);
    }

    public Optional<UserInfoDTO> getUserDetails(final UUID id) {
        return this.getUser(id).map(UserEntity::toInfo);
    }

    public List<UserInfoDTO> listUserDetails() {
        return this.repo.findAll().stream().map(UserEntity::toInfo).collect(Collectors.toList());
    }

    public List<UserInfoDTO> listAllByRole(final UserRoles role) {
        return this.repo
            .findAllByRole(role)
            .stream().map(UserEntity::toInfo)
            .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("PMD.AvoidUncheckedExceptionsInSignatures")
    public UserAuthority loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserInfoDTO> found = this.findUserDetails(username);
        if (found.isPresent()) {
            return new UserAuthority(found.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @PostConstruct
    @Transactional
    public void initUsers() {
        final String admin = UsersApiConfig.ADMIN_NAME;
        final String api = RestAuthProperties.NAME;
        this.activateInternalUser(
            new UserRegistrationDTO(admin,1, admin, admin, this.apiConfig.getAdminPassword()),
            UserRoles.ADMIN
        );
        this.activateInternalUser(
            new UserRegistrationDTO(api,0, api, api, this.apiUserProperties.getPassword()),
            UserRoles.API
        );
    }

    private void activateInternalUser(UserRegistrationDTO details, UserRoles role) {
        final boolean userCreated = this.registerIfNotExists(details,role);
        final Optional<UserEntity> userFound = this.findUser(details.getEmail());
        if (userFound.isPresent()) {
            if (!userCreated) {
                this.updateUser(
                    new UserUpdateDTO(
                        userFound.get().getUuid(),
                        details.getEmail(),
                        details.getPhone(),
                        details.getFirstName(),
                        details.getLastName(),
                        details.getPassword()
                    )
                );
            }
            for (ConfirmationToken token : this.tokenService.getActiveTokens(userFound.get())) {
                try {
                    this.activateUserByToken(token.getToken());
                } catch (TokenExpiredException ignored) {
                }
            }
            this.activateUserByName(details.getEmail());
        }
    }

    private Optional<UserEntity> findUser(final String username) {
        return this.repo.findOneByEmail(username);
    }

    private Optional<UserEntity> getUser(final UUID id) {
        return this.repo.findById(id);
    }

}
