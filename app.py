from flask import Flask, render_template, redirect, request
from flask_ngrok import run_with_ngrok
import excel2json
from flask import jsonify 
import finalCode
import pandas as pd
import pandas
import base64
from flask_restplus import Api,Resource
from flask_cors import CORS
from flask import jsonify

app = Flask(__name__)
#CORS(app)
#API_NAME = Api(app)
run_with_ngrok(app)


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
    # a = excel2json.convert_from_file('test.xlsx')
    a = df.to_json(orient='records')
    return a
    


@app.route('/image', methods = ['GET'])
def wait_for_image_result():
    return render_template('index.html')

@app.route('/image', methods =['POST'])
def result():
    
    if request.method == 'POST':
   
        f = request.files['img']
        print(f)
        path = './static/{}'.format(f.filename)
        print(path) 
        f.save(path)
        print(f)

    image_result = finalCode.predict(f)
    user_final = "Sargam"
    df = pd.read_excel("test.xlsx")

    selected_user = df[df["Name"] == user_final]
    dataf = pd.DataFrame({'Name': selected_user["Name"]})



    return render_template('index.html',result=image_result, tables=[dataf.to_html()])


# class imageApp(Resource):
#     def getImage(self):
#         params = request.args.getstring('encoded_image')
#         print params
#         base64_dencode= base64.decodestring(params)
#         image_result = open('result.jpg','wb')
#         image_result.write(base64_dencode)

        
#         path = './static/' 
#         f.save(path)
#         image_result = finalCode.predict(f)



if __name__ == '__main__':
    app.run()
