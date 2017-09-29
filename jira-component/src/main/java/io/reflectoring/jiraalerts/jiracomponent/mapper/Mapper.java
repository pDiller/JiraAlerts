package io.reflectoring.jiraalerts.jiracomponent.mapper;

/**
 * Interface for Mappers.
 * 
 * @param <E>
 *            Entity
 * @param <D>
 *            DTO
 */
public interface Mapper<E, D> {

	public E dtoToEntity(D dto);

	public D entityToDTO(E entity);
}
