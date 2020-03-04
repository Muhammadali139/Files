import time
from playsound import playsound
from tkinter import simpledialog
import tkinter
from gtts import gTTS, gTTSError


def save_text(filetext, filename):
    with open(filename, 'w') as f:
        f.write(filetext)


def convert(text):
    # myText = 'Text to Speech Conversion Using Python'
    language = 'en'

    # speech from text
    output = gTTS(text=text, lang=language, slow=False)

    # save as  mp3
    name = "text_[" + text + "]" + str(int(time.time()))

    output.save(name + ".mp3")
    save_text(filetext=text, filename=(name + ".txt"))

    # start mp3
    # os.system('start output.mp3')
    return name + ".mp3"


# Test
# print(int(time.time()))
def playMP3(file_name):
    playsound(file_name)


def progress():
    root = tkinter.Tk()
    root.withdraw()
    while 1:
        user_input_text = simpledialog.askstring(title="Test to speech converter", prompt="Please enter text.")
        if user_input_text == '' or user_input_text == 'off' or user_input_text is None:
            break
        audio_file = convert(user_input_text)
        playMP3(audio_file)


progress()
