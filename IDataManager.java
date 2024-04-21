import java.util.ArrayList;

public interface IDataManager<T1, T2> {
	void update(T1 t1);
	void add(T1 t1);
	void delete(T1 t1);
	T1 find(T2 t2);
	ArrayList<T1> getAll();
}
// DONE