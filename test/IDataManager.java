import java.util.ArrayList;
import java.io.Serializable;
public interface IDataManager<T1 extends Serializable, T2> {

	void update(T2 t2 , T1 newT1);
	void add(T1 t1);
	void delete(T1 t1);
	T1 find(T2 t2);
	ArrayList<T1> getAll();
}