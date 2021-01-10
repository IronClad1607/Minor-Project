from flask import Flask, render_template, redirect, request
import pyrebase
from flask_ngrok import run_with_ngrok
import excel2json
from flask import jsonify 
import finalCode
import pandas as pd
import json
from config import *

app = Flask(__name__)
run_with_ngrok(app)



firebase = pyrebase.initialize_app(firebaseConfig)
auth = firebase.auth()

@app.route('/login', methods = ['POST','GET'])
def login():
    if(request.method == 'POST'):
        email = request.form['name']
        password = request.form['password']
        try:
            auth.sign_in_with_email_and_password(email,password)
            return render_template('hello1.html')
        except:
            unsuccessful = 'Please check your credentials'
            return render_template('login.html', umessage = unsuccessful)
    return render_template('login.html')

@app.route('/signUp', methods = ['GET','POST'])
def signUp():
    if(request.method == 'POST'):
        email = request.form['name']
        password = request.form['password']
        try:
            auth.create_user_with_email_and_password(email,password)
            return render_template('login.html')
        except:
            unsuccessful = 'already exist'
            return render_template('signUp.html', umessage=unsuccessful)
    return render_template('signUp.html')

@app.route('/forgot', methods = ['GET','POST'])
def forget_password():
    if (request.method == 'POST'):
        email = request.form['name']
        auth.send_password_reset_email(email)
        return render_template('login.html')
    return render_template('forgetPass.html')


@app.route('/fetchResult', methods =['GET', 'POST'])
def fetchResult():

    if request.method == 'POST':
   
        f = request.files['img']
        path = './static/{}'.format(f.filename) 
        f.save(path)

    image_result = finalCode.predict(f)
    df = pd.read_excel("test.xlsx")

   
    a = df.to_json(orient='records')
    jdata = json.loads(a)
    user=list(filter(lambda u: str(u['id'])== image_result, jdata))
    
    return jsonify(user)



@app.route('/', methods = ['GET'])
def wait_for_result():
    return render_template('home1.html')
    

@app.route('/home', methods=['GET'])
def home():
    return render_template('hello1.html')


@app.route('/logout', methods =['GET'])
def logout():
    return render_template('logoutPage.html')


@app.route("/all", methods = ['GET','POST'])
def getAll():
  
    df = pd.read_excel("test.xlsx")
    a = excel2json.convert_from_file('test.xlsx')
    a = df.to_json(orient='records')

    jdata = json.loads(a)  
    user = list(jdata)
    return render_template('allUser.html', result = user)



@app.route("/allUser",methods= ['GET','POST'])
def getAllUser():
    df = pd.read_excel("test.xlsx")
    a = df.to_json(orient='records')
    jd = json.loads(a)
    return jsonify(jd)
    

@app.route('/allUser/<id>')
def getUser(id):
    df = pd.read_excel("test.xlsx")
    a = df.to_json(orient='records')
    jdata = json.loads(a)

    user=list(filter(lambda u: str(u['id'])== id, jdata))
    
    return jsonify(user)


@app.route('/image', methods = ['GET'])
def wait_for_image_result():
    return render_template('index.html')


@app.route('/image', methods =['POST'])
def result():
    
    if request.method == 'POST':
   
        f = request.files['img']
        path = './static/{}'.format(f.filename) 
        f.save(path)

    prev_path = path

    image_result = finalCode.predict(f) # name of persom
   
    df = pd.read_excel("test.xlsx")

    result_dic = {
        'image': path,
        'result': image_result
    }

   
    a = df.to_json(orient='records')
    jdata = json.loads(a) #python dictionary

    user=list(filter(lambda u: u['id'] == int(image_result), jdata)) 
   
    return render_template('image.html',path=prev_path, result1 = result_dic, result = user)

if __name__ == '__main__':
    app.run()