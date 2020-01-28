package com.zip.code.filter.analyser;

public class ZipCodeRange implements Comparable<ZipCodeRange> {
	private int min;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	private int max;

	public ZipCodeRange(int mi, int mx) {
		super();
		this.min = mi;
		this.max = mx;
	}

	public ZipCodeRange(ZipCodeRange range) {
		super();
		this.min = range.min;
		this.max = range.max;
	}

	public int compareTo(ZipCodeRange o) {
		if (this.min == o.min) {
			return this.max - o.max;
		}
		return this.min - o.min;
	}

	public String toString() {
		return "[" + this.min + "," + this.max + "]";
	}
}
