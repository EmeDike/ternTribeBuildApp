package org.ModifiedApplication.dike.repositories;

import org.ModifiedApplication.dike.model.Contributions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepository extends JpaRepository<Contributions, Long> {
}
