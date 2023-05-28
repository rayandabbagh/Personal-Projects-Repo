import gc
class Room: 
    def __init__(self, name):
        self.name = name

    def __eq__(self, other):
        return self.name == other.name
        
    def __repr__(self):
        return "Room(name: {})".format(self.name)

class Task:
    def __init__(self,name):
        self.name = name
        self.isCompleted = False

    def __eq__(self,other):
        return self.name == other.name and self.isCompleted == other.isCompleted
    

    def __repr__(self): # provided
        return "Task(name: {}, isCompleted: {})".format(self.name, self.isCompleted)

class Crewmate:
    def __init__(self,name,color,accessories = ()):
        self.name = name
        self.color = color
        self.accessories = accessories
        self.isAlive = True
        self.tasksDone = 0

    def doTask(self,aTaskObj):
        if aTaskObj.isCompleted == False:
            aTaskObj.isCompleted ==  True
            self.tasksDone +=1
        else:
            return "Nothing to do here"

    def vote(self,AmongUsObj):
        for crewmate in AmongUsObj.crewmates:
            if crewmate.name[0] == self.name[0] and crewmate.name != self.name  and crewmate.isAlive == True:
                return crewmate
        for impostor in AmongUsObj.impostors:
            if impostor.name[0] == self.name[0] and impostor.name != self.name  and impostor.isAlive == True:
                return impostor

    def callMeeting(self,AmongUsObj):
        votes = []
        AmongUsObj.allplayers = AmongUsObj.crewmates + AmongUsObj.impostors
        out = ""
        count = 0
        for i in AmongUsObj.allplayers:
            votes.append(i.vote(AmongUsObj))
        for d in votes:
            
            if votes.count(d) > count:
                count = votes.count(d)
                out = d
        
        out.isAlive = False
        if out.__class__ == Impostor:
            return "{} was An Impostor.".format(out.name)
        else:
            return "{} was not An Impostor.".format(out.name)
                 
 
    def __repr__(self): # provided
        return "Crewmate(name: {}, color: {})".format(self.name, self.color)

    def __eq__(self, other): # provided
        return (self.name, self.color, self.accessories) == (other.name, other.color, other.accessories)

class Impostor:
    def __init__(self,name,color,accessories =()):
        self.name = name
        self.color = color
        self.accessories = accessories
        self.isAlive = True
        self.eliminateCount = 0

    def eliminate(self,player):
        if isinstance(player,Impostor):
            return "They're on your team -_-"
        if isinstance(player,Crewmate):
            player.isAlive = False
            self.eliminateCount +=1

    def vote(self,AmongUsObj):
        for crewmate in AmongUsObj.crewmates:
            if crewmate.name[0] == self.name[0] and crewmate.name != self.name  and crewmate.isAlive == True:
                return crewmate
        for impostor in AmongUsObj.impostors:
            if impostor.name[0] == self.name[0] and crewmate.name != self.name  and crewmate.isAlive == True:
                return impostor
    def __str__(self):
        return "My name is {} and I'm an impostor.".format(self.name)
        

    def __repr__(self): # provided
        return "Impostor(name: {}, color: {})".format(self.name, self.color)

    def __eq__(self, other): # provided
        return (self.name, self.color, self.accessories) == (other.name, other.color, other.accessories)

class AmongUs:
    def __init__(self,maxPlayers):
        self.maxPlayers = maxPlayers
        self.rooms = {}
        self.crewmates = []
        self.impostors = []

    def registerPlayer(self,player):
        if int(self.maxPlayers) <= (int(len(self.crewmates)) + int(len(self.impostors))):
            return "Lobby is full."
        for crewmate in self.crewmates:
            if player.name == crewmate.name:
                return "Player with name: {} exists.".format(player.name)
        for impostor in self.impostors:
            if player.name == impostor.name:
                return "Player with name: {} exists.".format(player.name)
        if isinstance(player,Crewmate):
            self.crewmates.append(player)
        if isinstance(player,Impostor):
            self.impostors.append(player)

    def registerTask(self,TaskObj,RoomObj):
        new = self.rooms
        for k in new:
            if TaskObj in new[k]:
                return "This task has already been registered."
     
        if RoomObj.name in new:
            if TaskObj in new[RoomObj.name]:
                return "This task has already been registered."
            else:
                new[RoomObj.name].append(TaskObj)
        else:
            new[RoomObj.name] = [TaskObj]
 
     
    
        

    def gameOver(self):
        cr = 0
        im = 0
        for crewmate in self.crewmates:
            if crewmate.isAlive == True:
                cr +=1
        for impostor in self.impostors:
            if impostor.isAlive == True:
                im +=1
        if cr == 0:
            return "Defeat! All crewmates have been eliminated"
        if im == 0:
            return "Victory! All impostors have been eliminated"
        else:
            return "Game is not over yet"

    def __repr__(self): # provided
        return "AmongUs(maxPlayers: {})".format(self.maxPlayers)
