package prob2;

//여기 수정하기
public class Money {
	int amount; // 불변

	public Money(int amount) {
		this.amount = amount;
	}

	public Money add(Money money) {
		int result = this.amount + money.amount;
		return new Money(result);
	}

	public Money minus(Money money) {
		int result = this.amount - money.amount;
		return new Money(result);
	}

	public Money multiply(Money money) {
		int result = this.amount * money.amount;
		return new Money(result);
	}

	public Money devide(Money money) {
		int result = this.amount / money.amount;
		return new Money(result);
	}

	/*
	 * 2. equals 메쏘드에서는 인자로 넘어온 Object 객체가 Money 타입인지를 확인하고, Money 타입인 경우 에 금액이
	 * 동일한지를 확인한다. 이 두가지 조건이 만족하는 경우에 true를 리턴한다.
	 */

	public boolean equals(Object object) {
		if(object instanceof Money && ((Money) object).amount == this.amount) {
			return true;
		}
		return false;

	}
}
