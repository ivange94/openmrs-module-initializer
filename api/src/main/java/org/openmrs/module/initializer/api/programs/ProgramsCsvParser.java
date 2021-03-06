package org.openmrs.module.initializer.api.programs;

import org.openmrs.Program;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.module.initializer.api.CsvParser;

import java.io.IOException;
import java.io.InputStream;

public class ProgramsCsvParser extends CsvParser<Program, ProgramWorkflowService, ProgramLineProcessor> {
	
	public ProgramsCsvParser(InputStream is, ProgramWorkflowService pws) throws IOException {
		super(is, pws);
	}
	
	@Override
	protected Program save(Program instance) {
		return service.saveProgram(instance);
	}
	
	@Override
	protected boolean isVoidedOrRetired(Program instance) {
		return instance.isRetired();
	}
	
	@Override
	protected void setLineProcessors(String version, String[] headerLine) {
		addLineProcessor(new ProgramLineProcessor(headerLine, service));
	}
}
