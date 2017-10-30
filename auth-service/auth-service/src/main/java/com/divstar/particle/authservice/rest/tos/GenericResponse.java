package com.divstar.particle.authservice.rest.tos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * This class resembles a generic response, which in turn wraps a given data type T
 * and ensures that data is serialized into a JSON-string upon need.
 * 
 * @author divstar
 *
 * @param <T>
 *            type of the data being wrapped by objects of this class
 */
@Data
public class GenericResponse<T> {
	/**
	 * Timestamp ({@link LocalDateTime}) of when the error occurred.
	 * 
	 * @param timestamp
	 *            ({@link LocalDateTime}) timestamp of when the error occurred
	 * @return ({@link LocalDateTime}) timestamp of when the error occurred
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	private String		  message;
	private HttpStatus	  status;
	private T			  data;

	/**
	 * Default constructor.
	 * <p>
	 * Sets the {@link GenericResponse#timestamp} value to now.
	 */
	public GenericResponse() {
		this.timestamp = LocalDateTime.now();
	}

	/**
	 * Constructor.
	 * <p>
	 * Sets the {@link GenericResponse#timestamp} value to now.
	 * 
	 * @param message
	 *            (String) message to include in the message field of the response
	 * @param status
	 *            ({@link HttpStatus}) http-status to respond with
	 * @param data
	 *            (T) (serializable) data to send as a response
	 */
	public GenericResponse(final String message, final HttpStatus status, final T data) {
		this();
		this.message = message;
		this.status = status;
		this.data = data;
	}
}
