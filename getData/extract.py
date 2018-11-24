import json

with open('recipes.json') as data_file:    
	data = json.load(data_file)

newJson = {}
for key in enumerate(data["hits"]):
	label = data["hits"][key[0]]["recipe"]["label"]
	recipeUrl = data["hits"][key[0]]["recipe"]["url"]
	imageUrl = data["hits"][key[0]]["recipe"]["image"]
	dietLabels = data["hits"][key[0]]["recipe"]["dietLabels"]
	healthLabels = data["hits"][key[0]]["recipe"]["healthLabels"]
	cautions = data["hits"][key[0]]["recipe"]["cautions"]
	ingredients = data["hits"][key[0]]["recipe"]["ingredients"]
	calories = data["hits"][key[0]]["recipe"]["calories"]
	totalWeight = data["hits"][key[0]]["recipe"]["totalWeight"]
	totalTime = data["hits"][key[0]]["recipe"]["totalTime"]
	
	newJson.update({label:{"imageUrl": imageUrl, "recipeUrl": recipeUrl, "dietLabels": dietLabels, "healthLabels": healthLabels, "cautions": cautions, "ingredients": ingredients, "calories": calories, "totalWeight": totalWeight, "totalTime": totalTime}})
	print newJson
	
with open('ingredients.json', 'w') as outfile:
	json.dump(newJson, outfile)