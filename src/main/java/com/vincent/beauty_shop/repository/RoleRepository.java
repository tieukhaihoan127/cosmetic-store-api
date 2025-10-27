package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.response.authentication.RoleDetailDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = """
        SELECT 
            r.id,
            r.title,
            r.description,
            json_agg(
                json_build_object(
                    'id', p.id,
                    'title', p.title,
                    'description', p.description
                )
            ) AS permissions
        FROM role r
        JOIN role_permission rp ON rp.role_id = r.id
        JOIN permission p ON p.id = rp.permission_id
        WHERE r.id = :roleId
        GROUP BY r.id, r.title, r.description
        """, nativeQuery = true)
    Map<String, Object> findRoleWithPermissionsAsJson(@Param("roleId") Long roleId);

    @Query("select r from Role r left join fetch r.permissions where r.id = :id")
    Optional<Role> findWithPermissionsById(Long id);
}
