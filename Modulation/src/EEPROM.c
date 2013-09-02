#include <avr/io.h>
#include <util/twi.h>


#DEFINE TRUE 1
#DEFINE FALSE 0
#DEFINE ADDRESS 2451

/*
 * Open Connection
 */
void openEEPROM() {
	TWSR = 0x00;
	TWBR = 0x0C;

	TWCR = (1<<TWEN);
}

/*
 * Start Connection
 */
void startEEPROM() {
	TWCR = (1<<TWINT) | (1<<TWSTA) | (1<<TWEN);
	while (!(TWCR & (1 << TWINT));
}

/*
 * Stop Connection
 */
void stopEEPROM() {
	TWCR = (1 << TWINT) | (1 << TWSTO) | (1 << TWEN);
	while (TWCR & (1 << TWSRO));
}


/*
 *  Write
 */
void writeEEPROM(unit8_t u) {
	TWDR = u;

	// Transfer
	TWCR = (1 << TWINT) | (1 << TWEN);

	// Poll
	while (!(TWCR & (1 << TWINT)));
}

/*
 *  Write
 */
void writeAADRL(unit16_t u) {
	TWDR = u;

	// Transfer
	TWCR = (1 << TWINT) | (1 << TWEN);

	// Poll
	while (!(TWCR & (1 << TWINT)));
}

unit8_t readByteEEPROM(unit16_t addr) {
    unit8_t data;

	/* Write Address */
	writeEEPROM(addr >> 8);

    if ((TWSR & 0xF8) != 0x28) return FALSE;


	writeAADRL(addr);

	if ((TWSR & 0xF8) != 0x28) return FALSE;

	startEEPROM();
	if ((TWSR & 0xF8) != 0x10) return FALSE;

	writeEEPROM(0b10100001);

    if ((TWSR & 0xF8) != 0x40) return FALSE;


    TWCR = (1 << TWINT) | (1 << TWEN);
    while (!(TWCR & (1 << TWINT)));
    if ((TWSR & 0xF8) != 0x58) return FALSE;

    data = TWDR;

    stopEEPROM();

    return data;
}


void main (int argc, char *[] argv) {
	unit8_t data;
	openEEPROM();
	data = readByteEEPROM(ADDRESS);

    // DO SOMETHING WITH DATA!
}