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

@app.route('/allUser/<Id>')
def getUser(Id):
    df = pd.read_excel("test.xlsx")
#a = excel2json.convert_from_file('test.xlsx')
    a = df.to_json(orient='records')
    jdata = json.loads(a)

    user=list(filter(lambda u: str(u['Id'])== Id, jdata))
    
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

    image_result = finalCode.predict(f)
    user_final = "Sargam"
    df = pd.read_excel("test.xlsx")

    selected_user = df[df["Name"] == user_final]
    dataf = pd.DataFrame({'Id': selected_user["Id"], 'Name': selected_user["Name"], 'Age': selected_user["Age"], 'Place of Birth': selected_user["Place of Birth"], 'Gender': selected_user["Gender"], 'Marital Status': selected_user["Marital Status"],}) 
    


    return render_template('index.html',result=image_result, tables=[dataf.to_html()])

if __name__ == '__main__':
    app.run()