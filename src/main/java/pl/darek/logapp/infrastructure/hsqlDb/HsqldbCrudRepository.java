package pl.darek.logapp.infrastructure.hsqlDb;

import org.springframework.data.repository.CrudRepository;

interface HsqldbCrudRepository extends CrudRepository<LogEventEntity, String> {

}
