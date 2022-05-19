package nc.unc.ama.staff_service.service;

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
import nc.unc.ama.staff_service.entities.ConfirmationToken;
import nc.unc.ama.staff_service.entities.UserAuthority;
import nc.unc.ama.staff_service.entities.UserEntity;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRegistrationDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UserStatus;
import nc.unc.ama.common.dto.UserUpdateDTO;
import nc.unc.ama.staff_service.err.TokenExpiredException;
import nc.unc.ama.staff_service.err.UserAlreadyExistsException;
import nc.unc.ama.staff_service.repositories.UserRepo;
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

    @Autowired
    public UsersService(
        final UserRepo repo,
        final PasswordEncoder passwordEncoder,
        final RestAuthProperties properties,
        final ConfirmTokenService tokenService
    ) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.apiUserProperties = properties;
        this.tokenService = tokenService;
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
            return this.repo.save(user.update(update)).toInfo();
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
        final String admin = "admin@api.api";
        this.registerIfNotExists(
            new UserRegistrationDTO(admin,1, admin, admin, admin),
            UserRoles.ADMIN
        );
        this.activateUserByName(admin);
        final String api = RestAuthProperties.NAME;
        final boolean apiUserCreated = this.registerIfNotExists(
            new UserRegistrationDTO(api,0, api, api, this.apiUserProperties.getPassword()),
            UserRoles.API
        );
        final Optional<UserEntity> apiFound = this.findUser(api);
        if (!apiUserCreated && apiFound.isPresent()) {
            this.updateUser(
                new UserUpdateDTO(
                    apiFound.get().getUuid(),
                    api,
                    0,
                    api,
                    api,
                    this.apiUserProperties.getPassword()
                )
            );
        }
        this.activateUserByName(api);
    }

    private Optional<UserEntity> findUser(final String username) {
        return this.repo.findOneByEmail(username);
    }

    private Optional<UserEntity> getUser(final UUID id) {
        return this.repo.findById(id);
    }
}
