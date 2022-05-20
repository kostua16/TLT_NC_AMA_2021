package nc.unc.ama.staff_service.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nc.unc.ama.staff_service.entities.ConfirmationToken;
import nc.unc.ama.staff_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConfirmTokenRepo extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    List<ConfirmationToken> findByUser(UserEntity user);

    @Query("select t from ConfirmationToken t where t.confirmedAt is null and t.user =?1")
    List<ConfirmationToken> findActiveByUser(UserEntity user);

    @Query(
        "select t from ConfirmationToken t " +
            "where t.confirmedAt is null and t.user =?1 and t.expiresAt > ?2"
    )
    List<ConfirmationToken> findActiveByUserThatExpireAfter(UserEntity user, LocalDateTime time);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
