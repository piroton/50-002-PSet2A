package piano;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	private List piano = new ArrayList<Pitch>();
	private Instrument currentInstrument;
	private int currentOctave = 0;
	private boolean isRecording = false;
	private List<NoteEvent> recording = new ArrayList<>();
	private long startTime;
    
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
            currentInstrument = Midi.DEFAULT_INSTRUMENT;
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    /**
    * Plays a note on the midi machine of the pitch.
    *
    * @param rawPitch : Pitch of the note to be played.
    * */
    public void beginNote(Pitch rawPitch) {
        Pitch transPitch = rawPitch.transpose(currentOctave*rawPitch.OCTAVE);
        if (!piano.contains(transPitch)){
            piano.add(transPitch);
            midi.beginNote(transPitch.toMidiFrequency(),currentInstrument);
            if (isRecording){
                NoteEvent k = new NoteEvent(transPitch, System.currentTimeMillis()-startTime,
                        currentInstrument, NoteEvent.Kind.start);
                recording.add(k);
            }
        }

    }

    /**
     * Call to stop a note of a specified pitch.
     * @param rawPitch: the pitch of the note that needs to be stopped.
     */
    public void endNote(Pitch rawPitch) {
        Pitch transPitch = rawPitch.transpose(currentOctave*rawPitch.OCTAVE);
        if (piano.contains(transPitch)){
            piano.remove(transPitch);
            midi.endNote(transPitch.toMidiFrequency(),currentInstrument);
            if (isRecording){
                NoteEvent k = new NoteEvent(transPitch, System.currentTimeMillis()-startTime,
                        currentInstrument, NoteEvent.Kind.stop);
                recording.add(k);
            }
        }
    }

    private NoteEvent find(Pitch rawPitch, List<NoteEvent> notes){
        for(int i=0; i<notes.size(); i++) {
            NoteEvent check = notes.get(i);
            if (check.getPitch() == rawPitch){
                notes.remove(i);
                return check;
            }
        }
        return null;
    }

    /**
     * Changes the instrument to the next instrument on the table.
     */
    public void changeInstrument() {
        currentInstrument=currentInstrument.next();
    }

    /**
     * Shifts the octave up one, to a max of +2.
     */
    public void shiftUp() {
    	if (currentOctave <2) currentOctave++;
    }

    /**
     * Shifts the octave down one, to a max of -2.
     */
    public void shiftDown() {
        if (currentOctave > -2) currentOctave--;
    }

    /**
     * Toggles the recording of notes played.
     * @return : The current state of isRecording.
     */
    public boolean toggleRecording() {
        if (!isRecording){
            isRecording = true;
            startTime = System.currentTimeMillis();
            return true;
        } else{
            isRecording = false;
            startTime = 0;
            return false;
        }
    }

    /**
     * Plays the notes in the same rhythm and order as they were recorded.
     */
    public void playback() {
        if (recording.size()>0){
            for (int i = 0; i<recording.size()-1; i++){
                play(recording.get(i));
                int duration = (int)(recording.get(i+1).getTime() - recording.get(i).getTime())/10;
                Midi.rest(duration);
            }
        }
    }
    void play(NoteEvent n){
        if (n.getKind() == NoteEvent.Kind.start){
            midi.beginNote(n.getPitch().toMidiFrequency(), n.getInstr());
        } else {
            midi.endNote(n.getPitch().toMidiFrequency(), n.getInstr());
        }
    }
}
