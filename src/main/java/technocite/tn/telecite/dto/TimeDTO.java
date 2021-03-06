package technocite.tn.telecite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Builder
@ToString
public class TimeDTO {
	private int hour;
	private int minute;
	private int second;
	
	public TimeDTO() {
		super();
	}
	public TimeDTO(int hour, int minute, int second) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return "TimeDTO [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
	}
	
	
}
