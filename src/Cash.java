

public class Cash implements Payment{

	@Override
	public boolean Pay(int amount) {
		return false;
	}
}