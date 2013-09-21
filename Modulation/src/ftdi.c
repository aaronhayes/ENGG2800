/*
 * Basic AVR code to send data over serial.
 *  Works with 2400 baud atm
 *
 * Created: 19/09/2013 11:47:39 AM
 *  Author: s4233846
 */

#include <avr/io.h>
#define F_CPU 1000000UL
#include <util/delay.h>

#define FOSC 1843200 // Clock Speed
#define BAUD 19200
#define MICRO_UBRR FOSC/16/BAUD-1

void USART_Transmit(unsigned int data);
void USART_Init( unsigned int ubrr);

void USART_Transmit(unsigned int data) {
	/* Wait for empty transmit buffer */
	while ( !( UCSR0A & (1<<UDRE0)) )
	;
	/* Puts data into buffer, sends the data */
	UDR0 = data;
}
void USART_Init( unsigned int ubrr)
{
	/*Set baud rate */
	UBRR0H = (unsigned char)(ubrr>>8);
	//UBRR0H = 0;
	UBRR0L = (unsigned char)ubrr;
	//UBRR0L = 25;
	/* Enable receiver and transmitter */
	UCSR0B = (1<<RXEN0)|(1<<TXEN0);
	/* Set frame format: 8 data bits N 2 stop bit */
	UCSR0C = (1<<USBS0)|(3<<UCSZ00);
}
int main( void )
{
	USART_Init(MICRO_UBRR);
	
	while (1) {
		USART_Transmit(0x69);
		_delay_ms(150);
	}		
}
