from flask import Flask, render_template, redirect, request
import finalCode


app = Flask(__name__)


@app.route('/')
def home(): 
    return "<h1>Test</h1>" 

@app.route('/image', methods = ['GET'])
def wait_for_result():
    return render_template('index.html')

@app.route('/image', methods =['POST'])
def result():
    
    if request.method == 'POST':
        f = request.files['img']
        path = './static/{}'.format(f.filename)
        f.save(path)

    image_result = finalCode.predict(f)

    return render_template('index.html', result = image_result)





if __name__ == '__main__':
    app.run(debug=True)