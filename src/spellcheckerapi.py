from flask import Flask,jsonify
from threading import Thread
from textblob import TextBlob

app = Flask('')
@app.route('/')	
def home():
	return  "I'm alive"

def correcttheword(value):
  return str(TextBlob(value).correct())

@app.route('/api/spellcheck/<string:andriodvar>', methods=['GET'])
def spellcheck(andriodvar):
	return jsonify({"correctedword":correcttheword(andriodvar)})

def run():
	app.run(host='0.0.0.0',port=8080)

t = Thread(target=run)
t.start()

