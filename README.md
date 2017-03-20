# IgnApp
Android App to browse IGN website. Uses IGN json API


My IGN APP uses the GSON library to parse the JSON data taken from the api and seperate the videos from articles. I insert all into a recycler view, but since since the videos need to scroll both horizontally and vertically, I added the videos in a recycler view within the overhead recycler view.
   I also added an infinite scroll feature by remembering the index of the last call and calling a new list of articles and videos once the user scrolled to the end of the current list. This not only allows infinite scrolling but the user can scroll back up to view previous videos and articles that were called previously. 
   I made the videos the same size as the articles because it made the look more symmetric and even. It also lets the videos and articles blend together so people are more likely to just click on topics that interest them instead of focusing on wanting to just read articles or just watch videos.
