from flask import Flask, render_template, redirect, request, jsonify
from flask_ngrok import run_with_ngrok
import finalCode
import pandas as pd
import pandas
import json

app = Flask(__name__)
run_with_ngrok(app)

df = pd.read_excel("test.xlsx")
#a = excel2json.convert_from_file('test.xlsx')
a = df.to_json(orient='records')

@app.route('/', methods = ['GET'])
def wait_for_result():
    return render_template('hello.html')

@app.route("/all", methods = ['GET', 'POST'])
def getAll():
    df = pd.read_excel("test.xlsx")
    return render_template('base.html',tables=[df.to_html()],titles = ['na', 'Table'])

@app.route("/allUser",methods= ['GET','POST'])
def getAllUser():
    df = pd.read_excel("test.xlsx")

    a = df.to_json(orient='records')
    jd = json.loads(a)
    #print(type(a))
    return jsonify(jd)

@app.route('/allUser/<id>')
def getUser(id):
    df = pd.read_excel("test.xlsx")
#a = excel2json.convert_from_file('test.xlsx')
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

    image_result = finalCode.predict(path)
    user_final = "Sargam"
    df = pd.read_excel("test.xlsx")

    selected_user = df[df["name"] == image_result]
    dataf = pd.DataFrame({'id': selected_user["id"], 'name': selected_user["name"], 'age': selected_user["age"], 'place_of_birth': selected_user["place_of_birth"], 'gender': selected_user["gender"], 'marital_status': selected_user["marital_status"],}) 
    


    return render_template('index.html',result=image_result, tables=[dataf.to_html()])

@app.route('/fetchResult', methods =['GET', 'POST'])
def fetchResult():

    if request.method == 'POST':
   
        f = request.files['img']
        path = './static/{}'.format(f.filename) 
        f.save(path)

    image_result = finalCode.predict(f)
    user_final = "Sargam"
    df = pd.read_excel("test.xlsx")

    #selected_user = df[df["Name"] == user_final]
    #dataf = pd.DataFrame({'Id': selected_user["Id"], 'Name': selected_user["Name"], 'Age': selected_user["Age"], 'Place of Birth': selected_user["Place of Birth"], 'Gender': selected_user["Gender"], 'Marital Status': selected_user["Marital Status"],}) 
    
#a = excel2json.convert_from_file('test.xlsx')
    a = df.to_json(orient='records')
    jdata = json.loads(a)

    user=list(filter(lambda u: str(u['name'])== image_result, jdata))
    
    return jsonify(user)

    #return render_template('index.html',result=image_result, tables=[dataf.to_html()])

@app.route('/img', methods = ['GET'])
def wait_for_image():
    return render_template('index.html')

@app.route('/img', methods =['POST'])
def resul():
    
    if request.method == 'POST':
   
        f = request.files['img']
        path = './static/{}'.format(f.filename) 
        f.save(path)

    image_result = finalCode.predict(f)
    user_final = "Sargam"
    #df = pd.read_excel("test.xlsx")

    #selected_user = df[df["name"] == image_result]
    #dataf = pd.DataFrame({'id': selected_user["id"], 'name': selected_user["name"], 'age': selected_user["age"], 'place_of_birth': selected_user["place_of_birth"], 'gender': selected_user["gender"], 'marital_status': selected_user["marital_status"],}) 
    


    return render_template('index.html',result=image_result)


if __name__ == '__main__':
    app.run()