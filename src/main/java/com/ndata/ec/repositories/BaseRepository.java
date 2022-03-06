package com.ndata.ec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@NoRepositoryBean
public interface BaseRepository<E, Long> extends JpaRepository<E, Long> {

}
