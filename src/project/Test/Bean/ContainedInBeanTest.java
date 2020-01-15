package project.Test.Bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Model.ContainedInBean;

class ContainedInBeanTest {

	@Test
	void testContainedInBeanEmpty() {
		ContainedInBean contained = new ContainedInBean();
		assertNotNull(contained);
	}

	@Test
	void testGetAppointmentId() {
		ContainedInBean contained = new ContainedInBean(1,1);
		assertEquals(1,contained.getAppointmentId());	
	}

	@Test
	void testSetAppointmentId() {
		ContainedInBean contained = new ContainedInBean(1,1);
		contained.setAppointmentId(2);
		assertEquals(2,contained.getAppointmentId());	
	}

	@Test
	void testGetActivityId() {
		ContainedInBean contained = new ContainedInBean(1,1);
		assertEquals(1,contained.getActivityId());
	}

	@Test
	void testSetActivityId() {
		ContainedInBean contained = new ContainedInBean(1,1);
		contained.setActivityId(2);;
		assertEquals(2,contained.getActivityId());
	}
}
