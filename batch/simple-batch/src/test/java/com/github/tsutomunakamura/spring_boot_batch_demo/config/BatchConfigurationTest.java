package com.github.tsutomunakamura.spring_boot_batch_demo.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;

import com.github.tsutomunakamura.spring_boot_batch_demo.model.Person;

/**
 * Unit tests for BatchConfiguration class.
 * Tests the configuration and functionality of Spring Batch components.
 */
class BatchConfigurationTest {
    
    private BatchConfiguration batchConfiguration;
    
    @BeforeEach
    void setUp() {
        batchConfiguration = new BatchConfiguration();
    }
    
    @Test
    void testReaderConfiguration() {
        // Given & When
        FlatFileItemReader<Person> reader = batchConfiguration.reader();
        
        // Then
        assertNotNull(reader, "Reader should not be null");
        assertThat(reader.getName()).isEqualTo("personItemReader");
    }
    
    @Test
    void testReaderCanReadSampleData() throws Exception {
        // Given
        FlatFileItemReader<Person> reader = batchConfiguration.reader();
        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext);
        
        try {
            // When - Read first person from sample-data.csv
            Person person1 = reader.read();
            
            // Then
            assertNotNull(person1, "First person should not be null");
            assertThat(person1.firstName()).isEqualTo("Jill");
            assertThat(person1.lastName()).isEqualTo("Doe");
            
            // When - Read second person
            Person person2 = reader.read();
            
            // Then
            assertNotNull(person2, "Second person should not be null");
            assertThat(person2.firstName()).isEqualTo("Joe");
            assertThat(person2.lastName()).isEqualTo("Doe");
            
            // Verify persons are different
            assertThat(person1).isNotEqualTo(person2);
            
        } finally {
            reader.close();
        }
    }
    
    @Test
    void testReaderReturnsNullWhenNoMoreData() throws Exception {
        // Given
        FlatFileItemReader<Person> reader = batchConfiguration.reader();
        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext);
        
        try {
            // When - Read all data until end
            Person person;
            int count = 0;
            while ((person = reader.read()) != null) {
                assertNotNull(person.firstName());
                assertNotNull(person.lastName());
                count++;
                
                // Safety check to avoid infinite loop
                if (count > 100) {
                    break;
                }
            }
            
            // Then - Should have read exactly 5 records from sample-data.csv
            assertThat(count).isEqualTo(5);
            
            // When - Try to read one more time
            Person extraPerson = reader.read();
            
            // Then - Should return null (end of file)
            assertThat(extraPerson).isNull();
            
        } finally {
            reader.close();
        }
    }
}
