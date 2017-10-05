package io.reflectoring.jiraalerts.jiracomponent.mapper;

/**
 * Interface for Mappers.
 *
 * @param <D>
 *            DTO
 * @param <E>
 *            Entity
 */
public interface EntityToDTOMapper<D, E> {

	public D entityToDTO(E entity);
}
