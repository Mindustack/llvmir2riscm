import info.teksol.schemacode.compiler.CompilerMessage;
import info.teksol.schemacode.compiler.CompilerOutput;
import info.teksol.schemacode.SchematicsInternalError;
import info.teksol.schemacode.ast.*;
import info.teksol.schemacode.mindustry.SchematicsIO;
import info.teksol.schemacode.schema.Language;
import info.teksol.schemacode.schema.Schematic;
import info.teksol.schemacode.schema.SchematicsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class AstSchematicBuilderTest {
    public static void main(String[] args) {


        AstDefinitions expected = new AstDefinitions(
                List.of(
                        new AstSchematic(
                                List.of(
                                        new AstSchemaAttribute("name", AstStringLiteral.fromText("On/off switch")),
                                        new AstSchemaAttribute("description", AstStringLiteral.fromText("Description")),
                                        new AstSchemaAttribute("label", AstStringLiteral.fromText("label1")),
                                        new AstSchemaAttribute("label", AstStringLiteral.fromText("label2")),
                                        new AstSchemaAttribute("dimensions", new AstCoordinates(2, 1))
                                ),
                                List.of(
                                        new AstBlock(
                                                List.of("cell1"),
                                                "@cell",
                                                new AstCoordinates(0, 0),
                                                new AstDirection("south"),
                                                null
                                        ), new AstBlock(
                                                List.of(),
                                                "@micro-processor",
                                                new AstCoordinates(0, 0),
                                                null,
                                                new AstProcessor(List.of(
                                                        new AstLinkPos(new AstConnection("cell1"), "cell2", false)
                                                ), new AstProgramText(AstStringLiteral.fromText("set x 1")), Language.MLOG)
                                        )
                                )
                        )
                ));

        List<CompilerMessage> messages = new ArrayList<>();
        SchematicsBuilder builder = SchematicsBuilder.create(expected, messages::add, Path.of("./"));
        Schematic schematic = builder.buildSchematics();
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            SchematicsIO.write(schematic, output);
            CompilerOutput<byte[]> compilerOutput = new CompilerOutput<>(output.toByteArray(), messages);
            String encoded = compilerOutput.output() != null
                    ? Base64.getEncoder().encodeToString(compilerOutput.output()) : "";
            System.out.println(encoded);
        } catch (IOException e) {
            throw new SchematicsInternalError(e, "Error converting schematics to binary representation.");
        }
    }
}
