package com.edigitpath.caze.repository;

import com.edigitpath.caze.domain.FileDataObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FileDataObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileDataObjectRepository extends JpaRepository<FileDataObject, Long>, JpaSpecificationExecutor<FileDataObject> {

}
