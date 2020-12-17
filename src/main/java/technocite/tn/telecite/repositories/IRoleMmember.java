package technocite.tn.telecite.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.RoleMember;

public interface IRoleMmember extends JpaRepository<RoleMember, Long>{
RoleMember findByNomRole(String nomRole);
}
