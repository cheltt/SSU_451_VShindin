
public class Company {
private String title;
private int number;
private int money;

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}

public boolean check(int money)
{
	if (money<=this.money)
		return true;
	else return false;}

public void addMoney(int money)
{
	this.money += money;
}

public void subMoney(int money)
{
	this.money -= money;
}

public Company(String title, int number, int money) {
	super();
	this.title = title;
	this.number = number;
	this.money = money;
}
public int getMoney() {
	return money;
}
public void setMoney(int money) {
	this.money = money;
}

}
