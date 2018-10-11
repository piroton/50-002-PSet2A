package piano;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
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
	private List<NoteEvent> recorded = new ArrayList<NoteEvent>();
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
    * beginNote(rawPitch)
    *
    *
    * */
    public void beginNote(Pitch rawPitch) {
        rawPitch.transpose(currentOctave*12);
        if (!piano.contains(rawPitch)){
            piano.add(rawPitch);
            midi.beginNote(rawPitch.toMidiFrequency(),currentInstrument);
            if (isRecording){
                NoteEvent k = new NoteEvent(rawPitch, System.currentTimeMillis()-startTime,
                        currentInstrument, NoteEvent.Kind.start);
                recorded.add(k);
            }
        }

    }
    
    //TODO write method spec
    public void endNote(Pitch rawPitch) {
        rawPitch.transpose(currentOctave*12);
        if (piano.contains(rawPitch)){
            piano.remove(rawPitch);
            midi.endNote(rawPitch.toMidiFrequency(),currentInstrument);
            if (isRecording){
                NoteEvent k = new NoteEvent(rawPitch, System.currentTimeMillis()-startTime,
                        currentInstrument, NoteEvent.Kind.stop);
                recorded.add(k);
            }
        }

    	//TODO implement for question 1
    }
    
    //TODO write method spec
    public void changeInstrument() {
        currentInstrument=currentInstrument.next();
    }
    
    //TODO write method spec
    public void shiftUp() {
    	if (currentOctave <2) currentOctave++;
    }
    
    //TODO write method spec
    public void shiftDown() {
        if (currentOctave > -2) currentOctave--;
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
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
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    public void playback() {
        long replayTime = System.currentTimeMillis();
        for ()
    }

}
