package Model;

public class Model <T extends Number>{
	
	private T firstNumber;
	private T secondNumber;

	
	public Model (T firstNumber, T secondNumber){
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}
	
	@SuppressWarnings("unchecked")
	public T addNumbers () {
		if(firstNumber instanceof Long && secondNumber instanceof Long) {
	        return (T) (Long) ((Long) firstNumber + (Long) secondNumber);
	    } 
		else {
	        return (T) (Double) ((Double) firstNumber + (Double) secondNumber);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T substractNumbers () {
		if(firstNumber instanceof Long && secondNumber instanceof Long) {
	        return (T) (Long) ((Long) firstNumber - (Long) secondNumber);
	    } 
		else {
	        return (T) (Double) ((Double) firstNumber - (Double) secondNumber);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T multiplyNumbers () {
		if(firstNumber instanceof Long && secondNumber instanceof Long) {
	        return (T) (Long) ((Long) firstNumber * (Long) secondNumber);
	    } 
		else {
	        return (T) (Double) ((Double) firstNumber * (Double) secondNumber);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E> E divideNumbers () {
		if(firstNumber instanceof Long && secondNumber instanceof Long) {			
			return (E) (Double) (Double.valueOf(firstNumber.toString()) / Double.valueOf(secondNumber.toString()));
		}
		else {
			return (E) (Double) ((Double) firstNumber / (Double) secondNumber);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T modulusNumbers () {
		if(firstNumber instanceof Long && secondNumber instanceof Long) {
	        return (T) (Long) ((Long) firstNumber % (Long) secondNumber);
	    } 
		else {
	        return (T) (Double) ((Double) firstNumber % (Double) secondNumber);
		}
	}

}
