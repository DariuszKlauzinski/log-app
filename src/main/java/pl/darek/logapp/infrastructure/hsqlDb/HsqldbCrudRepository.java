package pl.darek.logapp.infrastructure.hsqlDb;

import org.springframework.data.repository.CrudRepository;

public interface HsqldbCrudRepository extends CrudRepository<LogEventEntity, String> {

}
