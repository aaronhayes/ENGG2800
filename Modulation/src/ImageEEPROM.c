/*
 * Basic AVR code to send data over serial.
 *  Works with 2400 baud atm
 *
 * Created: 19/09/2013 11:47:39 AM
 *  Author: s4233846
 */

#include <avr/io.h>
#define F_CPU 8000000UL
#include <util/delay.h>
#include <util/twi.h>

#define FOSC 8000000 // Clock Speed
#define BAUD 19200
#define MICRO_UBRR FOSC/16/BAUD-1

void USART_Transmit(unsigned int data);
void USART_Init( unsigned int ubrr);

void openEEPROM(void);
void startEEPROM(void);
void stopEEPROM(void);
void writeEEPROM(uint8_t);
void writeAADRL(uint16_t);
uint8_t readByteEEPROM(uint16_t addr);


/*
 * Open Connection
 * Set bit rate to 400kHz
 */
void openEEPROM(void) {
	TWSR = 0x00;
	TWBR = 0x0C;

	TWCR = (1<<TWEN);
}

/*
 * Start Signal
 */
void startEEPROM(void) {
	TWCR = (1<<TWINT)|(1<<TWSTA)|(1<<TWEN);
	while (!(TWCR & (1<<TWINT)));
}

/*
 * Stop Signal
 */
void stopEEPROM(void) {
	TWCR = (1 << TWINT) | (1 << TWSTO) | (1 << TWEN);
	while (TWCR & (1 << TWSTO));
}


/*
 *  Write
 */
void writeEEPROM(uint8_t u) {

	TWDR = u;

	// Transfer
	TWCR = (1 << TWINT) | (1 << TWEN);

	// Poll
	while (!(TWCR & (1 << TWINT)));
}

/*
 *  Write
 */
void writeAADRL(uint16_t u) {
	TWDR = u;

	// Transfer
	TWCR = (1 << TWINT) | (1 << TWEN);

	// Poll
	while (!(TWCR & (1 << TWINT)));
}

uint8_t readByteEEPROM(uint16_t addr) {
    uint8_t data;
	startEEPROM();
	if ((TWSR & 0xF8) != TW_START) return -1;
	//USART_Transmit(0x01);
	
	writeEEPROM(0xA0);
	if ((TWSR & 0xF8) != TW_MT_SLA_ACK) return -1;
	
	//USART_Transmit(0x02);
	
	/* Write Address */
	writeEEPROM(0x00);
    if ((TWSR & 0xF8) != TW_MT_DATA_ACK) return -1;

	//USART_Transmit(0x03);
	
	/*writeEEPROM(0x00);
	if ((TWSR & 0xF8) != 0x28) return -1;*/

	startEEPROM();
	if ((TWSR & 0xF8) != TW_REP_START) return -1;
	
	//USART_Transmit(0x04);

	writeEEPROM(0xA1);

    if ((TWSR & 0xF8) != TW_MR_SLA_ACK) return -1;

	//USART_Transmit(0x05);
	/*USART_Transmit(0x53);
	USART_Transmit(0x74);
	USART_Transmit(0x61);
	USART_Transmit(0x72);
	USART_Transmit(0x74);*/
	for (int j = 0; j < 160; j++) {
		for (int i = 0; i < 200; i++) {
			//TWCR = (1 << TWINT) | (1 << TWEN);
			TWCR = (1 << TWINT) | (1 << TWEN) | (1 << TWEA);
			while (!(TWCR & (1 << TWINT)));
			if ((TWSR & 0xF8) != TW_MR_DATA_ACK) return -1;
			//USART_Transmit(0x06);
			data = TWDR;
			PORTB = data;
			_delay_ms(1);
			//USART_Transmit(data);
		}
		/*USART_Transmit(0x46);
		USART_Transmit(0x69);
		USART_Transmit(0x6E);
		USART_Transmit(0x69);
		USART_Transmit(0x73);
		USART_Transmit(0x68);
		USART_Transmit(0x65);
		USART_Transmit(0x64);*/
	}		
    stopEEPROM();
	PORTB = 0x00;
    return 0;
}


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
	UBRR0L = (unsigned char)ubrr;

	/* Enable receiver and transmitter */
	UCSR0B = (1<<RXEN0)|(1<<TXEN0);
	/* Set frame format: 8 data bits N 2 stop bit */
	UCSR0C = (1<<USBS0)|(3<<UCSZ00);
}
int main( void )
{
	_delay_ms(10000);
	CLKPR = 1<<CLKPCE;
	CLKPR = 0;
	DDRB = 0xFF;
	USART_Init(MICRO_UBRR);
	openEEPROM();
	
	/*
    for (int i = 0; i < 160; i++) {
	    for (int j = 0; j < 200; j++) {
		    PORTB = ((j == 50) || (i == 50)) ? 140 : 69;
	    }
    }*/
	
	/*while (1) {
		for (int i = 0; i < 256; i++) {
			PORTB = i;
			USART_Transmit(i);
		}		
	}*/

	//USART_Transmit(0xAA);
	if(readByteEEPROM(0x00)) {
	} else {
		//USART_Transmit(0xAA);	
	}
	
}
