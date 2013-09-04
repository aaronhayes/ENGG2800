#include <avr/io.h>
#include <util/twi.h>
#include <util/delay.h>

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
	TWCR = (1<<TWINT) | (1<<TWSTA) | (1<<TWEN);
	while (!(TWCR & (1 << TWINT)));
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

	/* Write Address */
	writeEEPROM(addr >> 8);

    if ((TWSR & 0xF8) != 0x28) return 0;


	writeAADRL(addr);

	if ((TWSR & 0xF8) != 0x28) return 0;

	startEEPROM();
	if ((TWSR & 0xF8) != 0x10) return 0;

	writeEEPROM(0xA1);

    if ((TWSR & 0xF8) != 0x40) return 0;


    TWCR = (1 << TWINT) | (1 << TWEN);
    while (!(TWCR & (1 << TWINT)));
    if ((TWSR & 0xF8) != 0x58) return 0;

    data = TWDR;

    stopEEPROM();

    return data;
}


int main (void) {
	uint8_t data;
	
	_delay_ms(1000);
	
	openEEPROM();
	
	// Read Data from Address 0x42
	data = readByteEEPROM(0x42);

    DDRB = 0xFF;
	
	while (1) {
		PORTB = data;
	}
}
