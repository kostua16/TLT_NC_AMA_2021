package nc.unc.ama.user.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findOneByEmail(String emailAddress);

    List<UserEntity> findAllByRole(UserRoles role);

}
