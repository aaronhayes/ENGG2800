#include <avr.io.h>
#include <util/delay.h>

#define FOSC 8000000 // Clock Speed
#define BAUD 19200
#define MICRO_UBRR FOSC/16/BAUD-1

void USART_Transmit(unsigned int data);
void USART_Init(unsigned int ubrr);
void adc_setup(void);
unsigned int adc_read(void);



void adc_setup(void) {
	/* Enable ADC */
	ADMUX = (0 << REFS1) | (1 << REFS0) | (0 << ADLAR) | (0 << MUX2) | (1 << MUX1) | (0 << MUX0);
 	ADCSRA = (1 << ADEN) | (0 << ADSC)| (0 << ADATE)| (0 << ADIF)| (0 << ADIE)| (1 << ADPS2)| (0 << ADPS1) | (1 << ADPS0);

 	/* Enable ADC interrupt overflow and clock */
 	TIMSK1 |= (1 << TOIE1);
 	TCCR1B |= (1 << CS11) | (1 << CS10);

}

unsigned int adc_read(void) {
	int i = 4;
	unsigned sample = 0;
	while (i--) {
		ADCSRA |= (1 << ADSC);
		while (ADCSRA & (1 << ADSC));
		adc_temp += ADC;
		_delay_ms(15);
	}
    /* Takes an average of 4 reads */
	sample /= 4;

	return sample;
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
	/* Set frame format: 8 N 1 */
	UCSR0C = (3 << UCSZ00);
}

int main (void) {
	unsigned int adc_data;

	adc_setup();

	USART_init(MICRO_UBRR);

    while (1) {
    	adc_data = adc_read();

    	USART_Transmit(adc_data);

    	_delay_ms(100);
    }



}