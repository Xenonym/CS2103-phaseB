# joanneong
###### /java/guitests/guihandles/CommandBoxHandle.java
``` java
    /**
     * Enters the given input in the Command Box without executing the input and
     * chooses the first option in the auto-complete list.
     * Note that the input is not executed.
     */
    public void enterInput(String input) {
        click();
        guiRobot.interact(() -> getRootNode().setText(input));
        guiRobot.pauseForAutoComplete(400);

        guiRobot.type(KeyCode.ENTER);
    }

```
###### /java/seedu/address/logic/commands/FindCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AnyContainsKeywordsPredicate firstPredicate =
                new AnyContainsKeywordsPredicate(Collections.singletonList("first"));
        AnyContainsKeywordsPredicate secondPredicate =
                new AnyContainsKeywordsPredicate(Collections.singletonList("second"));
        NameContainsKeywordsPredicate thirdPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("third"));
        NameContainsKeywordsPredicate fourthPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("fourth"));
        AddressContainsKeywordsPredicate fifthPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("fifth"));
        AddressContainsKeywordsPredicate sixthPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("sixth"));
        EmailContainsKeywordsPredicate seventhPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("seventh"));
        EmailContainsKeywordsPredicate eighthPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("eighth"));
        PhoneContainsKeywordsPredicate ninthPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("ninth"));
        PhoneContainsKeywordsPredicate tenthPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("tenth"));
        TagContainsKeywordsPredicate eleventhPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("eleventh"));
        TagContainsKeywordsPredicate twelfthPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("twelfth"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findThirdCommand = new FindCommand(thirdPredicate);
        FindCommand findFourthCommand = new FindCommand(fourthPredicate);
        FindCommand findFifthCommand = new FindCommand(fifthPredicate);
        FindCommand findSixthCommand = new FindCommand(sixthPredicate);
        FindCommand findSeventhCommand = new FindCommand(seventhPredicate);
        FindCommand findEighthCommand = new FindCommand(eighthPredicate);
        FindCommand findNinthCommand = new FindCommand(ninthPredicate);
        FindCommand findTenthCommand = new FindCommand(tenthPredicate);
        FindCommand findEleventhCommand = new FindCommand(eleventhPredicate);
        FindCommand findTwelfthCommand = new FindCommand(twelfthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findThirdCommand.equals(findThirdCommand));
        assertTrue(findFifthCommand.equals(findFifthCommand));
        assertTrue(findSeventhCommand.equals(findSeventhCommand));
        assertTrue(findNinthCommand.equals(findNinthCommand));
        assertTrue(findEleventhCommand.equals(findEleventhCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        FindCommand findThirdCommandCopy = new FindCommand(thirdPredicate);
        assertTrue(findThirdCommand.equals(findThirdCommandCopy));
        FindCommand findFifthCommandCopy = new FindCommand(fifthPredicate);
        assertTrue(findFifthCommand.equals(findFifthCommandCopy));
        FindCommand findSeventhCommandCopy = new FindCommand(seventhPredicate);
        assertTrue(findSeventhCommand.equals(findSeventhCommandCopy));
        FindCommand findNinthCommandCopy = new FindCommand(ninthPredicate);
        assertTrue(findNinthCommand.equals(findNinthCommandCopy));
        FindCommand findEleventhCommandCopy = new FindCommand(eleventhPredicate);
        assertTrue(findEleventhCommand.equals(findEleventhCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findThirdCommand.equals(1));
        assertFalse(findFifthCommand.equals(1));
        assertFalse(findSeventhCommand.equals(1));
        assertFalse(findNinthCommand.equals(1));
        assertFalse(findEleventhCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findThirdCommand.equals(null));
        assertFalse(findFifthCommand.equals(null));
        assertFalse(findSeventhCommand.equals(null));
        assertFalse(findNinthCommand.equals(null));
        assertFalse(findEleventhCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findThirdCommand.equals(findFourthCommand));
        assertFalse(findFifthCommand.equals(findSixthCommand));
        assertFalse(findSeventhCommand.equals(findEighthCommand));
        assertFalse(findNinthCommand.equals(findTenthCommand));
        assertFalse(findEleventhCommand.equals(findTwelfthCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = expectedMessage(0);

        FindCommand command = prepareGlobalCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());

        FindCommand nameCommand = prepareNameCommand(" ");
        assertCommandSuccess(nameCommand, expectedMessage, Collections.emptyList());

        FindCommand addressCommand = prepareAddressCommand(" ");
        assertCommandSuccess(addressCommand, expectedMessage, Collections.emptyList());

        FindCommand emailCommand = prepareEmailCommand(" ");
        assertCommandSuccess(emailCommand, expectedMessage, Collections.emptyList());

        FindCommand phoneCommand = preparePhoneCommand(" ");
        assertCommandSuccess(phoneCommand, expectedMessage, Collections.emptyList());

        FindCommand tagCommand = prepareTagCommand(" ");
        assertCommandSuccess(tagCommand, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_singleKeyword_onePersonFound() {
        String expectedMessage = expectedMessage(1);

        FindCommand command = prepareGlobalCommand("Kurz");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL));

        FindCommand nameCommand = prepareNameCommand("Elle");
        assertCommandSuccess(nameCommand, expectedMessage, Arrays.asList(ELLE));

        FindCommand addressCommand = prepareAddressCommand("10th");
        assertCommandSuccess(addressCommand, expectedMessage, Arrays.asList(DANIEL));

        FindCommand emailCommand = prepareEmailCommand("alice@example.com");
        assertCommandSuccess(emailCommand, expectedMessage, Arrays.asList(ALICE));

        FindCommand phoneCommand = preparePhoneCommand("948242");
        assertCommandSuccess(phoneCommand, expectedMessage, Arrays.asList(FIONA));

        FindCommand tagCommand = prepareTagCommand("owesMoney");
        assertCommandSuccess(tagCommand, expectedMessage(2), Arrays.asList(BENSON, FIONA));
    }

    @Test
    public void execute_singleKeyword_multiplePersonsFound() {
        FindCommand command = prepareGlobalCommand("Meier");
        assertCommandSuccess(command, expectedMessage(2), Arrays.asList(BENSON, DANIEL));

        FindCommand nameCommand = prepareNameCommand("Meier");
        assertCommandSuccess(nameCommand, expectedMessage(2), Arrays.asList(BENSON, DANIEL));

        FindCommand addressCommand = prepareAddressCommand("ave");
        assertCommandSuccess(addressCommand, expectedMessage(3), Arrays.asList(ALICE, BENSON, ELLE));

        FindCommand emailCommand = prepareEmailCommand("@example.com");
        assertCommandSuccess(emailCommand, expectedMessage(7),
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));

        FindCommand phoneCommand = preparePhoneCommand("94824");
        assertCommandSuccess(phoneCommand, expectedMessage(2), Arrays.asList(FIONA, GEORGE));

        FindCommand tagCommand = prepareTagCommand("friends");
        assertCommandSuccess(tagCommand, expectedMessage(5),
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, GEORGE));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        FindCommand command = prepareGlobalCommand("Kurz Elle Kunz");
        assertCommandSuccess(command, expectedMessage(3), Arrays.asList(CARL, ELLE, FIONA));

        FindCommand nameCommand = prepareNameCommand("Kurz Elle Kunz");
        assertCommandSuccess(nameCommand, expectedMessage(3), Arrays.asList(CARL, ELLE, FIONA));

        FindCommand addressCommand = prepareAddressCommand("ave 10");
        assertCommandSuccess(addressCommand, expectedMessage(4), Arrays.asList(ALICE, BENSON, DANIEL, ELLE));

        FindCommand emailCommand = prepareEmailCommand("alice anna");
        assertCommandSuccess(emailCommand, expectedMessage(2), Arrays.asList(ALICE, GEORGE));

        FindCommand phoneCommand = preparePhoneCommand("953  94824");
        assertCommandSuccess(phoneCommand, expectedMessage(3), Arrays.asList(CARL, FIONA, GEORGE));

        FindCommand tagCommand = prepareTagCommand("owesMoney enemy");
        assertCommandSuccess(tagCommand, expectedMessage(3), Arrays.asList(BENSON, CARL, FIONA));
    }

    /**
     * Formats the expected message and changes the message according to the number of persons found.
     * @param personsFound
     */
    private String expectedMessage(int personsFound) {
        return String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, personsFound);
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for a global find.
     */
    private FindCommand prepareGlobalCommand(String userInput) {
        FindCommand command =
                new FindCommand(new AnyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for a name search.
     */
    private FindCommand prepareNameCommand(String userInput) {
        FindCommand command =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for an address search.
     */
    private FindCommand prepareAddressCommand(String userInput) {
        FindCommand command =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for an email search.
     */
    private FindCommand prepareEmailCommand(String userInput) {
        FindCommand command =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for a phone search.
     */
    private FindCommand preparePhoneCommand(String userInput) {
        FindCommand command =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Parses {@code userInput} into a {@code FindCommand} for a tag search.
     */
    private FindCommand prepareTagCommand(String userInput) {
        FindCommand command =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<ReadOnlyPerson>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindCommand command, String expectedMessage, List<ReadOnlyPerson> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
```
###### /java/seedu/address/logic/parser/AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new AnyContainsKeywordsPredicate(keywords)), command);

        FindCommand nameCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), nameCommand);

        FindCommand addressCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " a/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new AddressContainsKeywordsPredicate(keywords)), addressCommand);

        FindCommand emailCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " e/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new EmailContainsKeywordsPredicate(keywords)), emailCommand);

        FindCommand phoneCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " p/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new PhoneContainsKeywordsPredicate(keywords)), phoneCommand);

        FindCommand tagCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " t/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new TagContainsKeywordsPredicate(keywords)), tagCommand);
    }

```
###### /java/seedu/address/logic/parser/FindCommandParserTest.java
``` java
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedGlobalFindCommand =
                new FindCommand(new AnyContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedGlobalFindCommand);

        FindCommand expectedNameFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedNameFindCommand);

        FindCommand expectedAddressFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Serangoon", "Bishan")));
        assertParseSuccess(parser, " a/Serangoon Bishan", expectedAddressFindCommand);

        FindCommand expectedEmailFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(
                        Arrays.asList("alice@example.com", "Bob@gmail.com")));
        assertParseSuccess(parser, " e/alice@example.com Bob@gmail.com", expectedEmailFindCommand);

        FindCommand expectedPhoneFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("12345678", "98454632")));
        assertParseSuccess(parser, " p/12345678 98454632", expectedPhoneFindCommand);

        FindCommand expectedTagFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "enemy")));
        assertParseSuccess(parser, " t/friends enemy", expectedTagFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedGlobalFindCommand);
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedNameFindCommand);
        assertParseSuccess(parser, " a/\n Serangoon \n \t Bishan  \t", expectedAddressFindCommand);
        assertParseSuccess(parser, " e/\n alice@example.com \n \t Bob@gmail.com  \t", expectedEmailFindCommand);
        assertParseSuccess(parser, " p/\n 12345678 \n \t 98454632  \t", expectedPhoneFindCommand);
        assertParseSuccess(parser, " t/\n friends \n \t enemy  \t", expectedTagFindCommand);


    }

}
```