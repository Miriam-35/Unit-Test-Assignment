package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
		assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		
	} else {
		assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of(
			arguments(2, 4, 6, false),
			arguments(-1, 3, 2, true),
			arguments(0, 3, 3, true)
				);
		// @formatter:on		
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		
		assertThat(testDemo.addPositive(25, 7)).isEqualTo(32);
		assertThat(testDemo.addPositive(42, 6)).isEqualTo(48);
		assertThat(testDemo.addPositive(48, 78)).isEqualTo(126);
		assertThat(testDemo.addPositive(999, 21)).isEqualTo(1020);
		
	}
	
	
	@Test
	void assertThatPairOfNumbersAreMultipliedCorrectly() {
		
		assertThat(testDemo.multiplyNumbers(5, 5)).isEqualTo(25);
		assertThat(testDemo.multiplyNumbers(-6, 75)).isEqualTo(-450);
		assertThat(testDemo.multiplyNumbers(-6, -14)).isEqualTo(84);
		assertThat(testDemo.multiplyNumbers(2, 0)).isEqualTo(0);
		
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomint();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
		
	}
	
}
