package io.reflectoring.jiraalerts.jiracomponent.mapper;

/**
 * Interface for Mappers.
 * 
 * @param <E>
 *            Entity
 * @param <D>
 *            DTO
 */
public interface DTOToEntityMapper<E, D> {

	public E dtoToEntity(D dto);
}
