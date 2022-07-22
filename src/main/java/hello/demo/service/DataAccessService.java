package hello.demo.service;

import java.util.List;

public interface DataAccessService<T> {

	public List<T> getAll();

	public T getById(Long id);

	public long add(T newElement);

	public void deleteById(Long id);

	public void update(long id, T element);
}
