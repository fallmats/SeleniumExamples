package se.prolore.junit.demo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RuleTester {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testUsingTempFolder() throws IOException, InterruptedException {
		File createdFolder = folder.newFolder("myfolder");
		File createdFile = folder.newFile("myfile.txt");
		assertTrue(createdFile.exists());
	}
}
