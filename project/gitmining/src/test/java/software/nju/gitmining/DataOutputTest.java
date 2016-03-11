package software.nju.gitmining;

import static org.junit.Assert.*;

import org.junit.Test;

import common.enumeration.attribute.Language;
import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.Repository;
import data.storage.output.DataOutputDefault;
import data.storage.service.DataStorageOutput;

public class DataOutputTest {

	DataStorageOutput out = new DataOutputDefault();
	
	@Test
	public void testSpecificRepo() {
		try {
			//Exists.
			Repository r = out.getRepository("aasm/aasm");
			assertEquals(r.getOwner().getLogin(),"aasm");
			assertEquals(r.getMainLanguage(),Language.RUBY);
			
			//Non-exists.
			Repository y = out.getRepository("aasm/XRiver"); //Target not found.
			fail("No exception thrown from non-existing repo.");
		} catch (Exception e) {
			assertEquals(e.getClass(),TargetNotFoundException.class);
		}
	}
	
	@Test
	public void testSpecificUser() {
		try {
			//Exists.
			GitUser r = out.getUser("Kerrick");
			assertEquals(r.getId(),552093);
			assertEquals(r.getEmail(),"me@kerricklong.com");
			
			//Non-exists.
			GitUser y = out.getUser("XRiver");
			fail("No exception thrown from non-existing repo.");
		} catch (Exception e) {
			assertEquals(e.getClass(),TargetNotFoundException.class);
		}
	}

	@Test
	public void testRepoMin() {
		fail("Test case hasn't been written!");
	}
	
	@Test
	public void testUserMin() {
		fail("Test case hasn't been written!");
	}
	
}
