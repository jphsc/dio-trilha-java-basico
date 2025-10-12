package com.santanderdio.springrestapi.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ResponseError {

	private Date timestamp = new Date();
	private String status = "error";
	private int statusCode = 400;
	private String error;

	public ResponseError() {
		
	}
	
	public ResponseError(Builder build) {
		this.timestamp = build.timestamp;
		this.status = build.status;
		this.statusCode = build.statusCode;
		this.error = build.error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public static class Builder {

		private Date timestamp;
		private String status;
		private int statusCode;
		private String error;
		
		public ResponseError build() {
			return new ResponseError(this);
		}

		public Builder setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder setStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder setStatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder setError(String error) {
			this.error = error;
			return this;
		}
	}
}
