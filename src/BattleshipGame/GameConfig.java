package BattleshipGame;

/**
 * File name: GameConfig
 * identification: Boyu Li 041003345
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A32
 * Professor: Paulo Sousa
 * Date: 8/6/2023
 * Compiler: Oracle Open JDK - version: 20.0.1 2023-04-18
 * Purpose: To stores all the general information
 */

public class GameConfig {
	GameConfig(){}

	static final String PROTOCOL_SEPARATOR = "#";
	static final String FIELD_SEPARATOR = ";";
	static final String PROTOCOL_END = "P0";
	static final String PROTOCOL_SENDGAME = "P1";
	static final String PROTOCOL_RECVGAME = "P2";
	static final String PROTOCOL_DATA = "P3";
	
	static String DEFAULT_USER = "User1";
	static String DEFAULT_ADDR = "localhost";
	static int DEFAULT_PORT = 12345;
}
