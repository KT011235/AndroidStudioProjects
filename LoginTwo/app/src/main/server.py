# import main Flask class and request object
from flask import Flask, request

# create the Flask app
app = Flask(__name__)

@app.route('/login', methods=['POST'])
def login():
    request_data = request.get_json()
    username = request_data['username']
    password = request_data['password']

    # Do something to validate user and password
    return '{"authorized": true }'

 # allow both GET and POST requests
# GET requests will be blocked
@app.route('/json-example', methods=['POST'])
def json_example():
    request_data = request.get_json()
    boolean_test = None

    if request_data:
        if 'language' in request_data:
            language = request_data['language']

        if 'framework' in request_data:
            framework = request_data['framework']

        if 'version_info' in request_data:
            if 'python' in request_data['version_info']:
                python_version = request_data['version_info']['python']

        if 'examples' in request_data:
            if (type(request_data['examples']) == list) and (len(request_data['examples']) > 0):
                example = request_data['examples'][0]

        if 'boolean_test' in request_data:
            boolean_test = request_data['boolean_test']

    return '''
           The language value is: {}
           The framework value is: {}
           The Python version is: {}
           The item at index 0 in the example list is: {}
           The boolean value is: {}'''.format(language, 
           framework, python_version, example, boolean_test)

# @app.route('/json-example', methods=['POST'])
# def json_example():
 #   return 'JSON Object Example'

if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run('0.0.0.0', debug=True, port=5000)

#     {
#     "language" : "Python",
#     "framework" : "Flask",
#     "website" : "Scotch",
#     "version_info" : {
#         "python" : "3.9.0",
#         "flask" : "1.1.2"
#     },
#     "examples" : ["query", "form", "json"],
#     "boolean_test" : true
## import main Flask class and request object
from flask import Flask, request

# create the Flask app
app = Flask(__name__)

@app.route('/login', methods=['POST'])
def login():
    request_data = request.get_json()
    username = request_data['username']
    password = request_data['password']

    # Do something to validate user and password
    return '{"authorized": true }'

 # allow both GET and POST requests
# GET requests will be blocked
@app.route('/json-example', methods=['POST'])
def json_example():
    request_data = request.get_json()
    boolean_test = None

    if request_data:
        if 'language' in request_data:
            language = request_data['language']

        if 'framework' in request_data:
            framework = request_data['framework']

        if 'version_info' in request_data:
            if 'python' in request_data['version_info']:
                python_version = request_data['version_info']['python']

        if 'examples' in request_data:
            if (type(request_data['examples']) == list) and (len(request_data['examples']) > 0):
                example = request_data['examples'][0]

        if 'boolean_test' in request_data:
            boolean_test = request_data['boolean_test']

    return '''
           The language value is: {}
           The framework value is: {}
           The Python version is: {}
           The item at index 0 in the example list is: {}
           The boolean value is: {}'''.format(language, 
           framework, python_version, example, boolean_test)

# @app.route('/json-example', methods=['POST'])
# def json_example():
 #   return 'JSON Object Example'

if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run('0.0.0.0', debug=True, port=5000)

#     {
#     "language" : "Python",
#     "framework" : "Flask",
#     "website" : "Scotch",
#     "version_info" : {
#         "python" : "3.9.0",
#         "flask" : "1.1.2"
#     },
#     "examples" : ["query", "form", "json"],
#     "boolean_test" : true
# } }