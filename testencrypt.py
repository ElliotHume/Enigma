import hashlib

def encrypt(message):
    alphabet = list("abcdefghijklmnopqrstuvwxyz")
    U_alphabet = list("abcdefghijklmnopqrstuvwxyz".upper())
    keys = list("qazwsxedcrfvtgbyhnujmikolp")
    U_keys = list("qazwsxedcrfvtgbyhnujmikolp".upper())

    keydict = {}
    for i in range(len(alphabet)):
        keydict[alphabet[i]] = keys[i]

    U_keydict = {}
    for i in range(len(U_alphabet)):
        U_keydict[U_alphabet[i]] = U_keys[i]

    loc = list(message)
    for i in range(len(loc)):
        if alphabet.__contains__(loc[i]):
            if (loc[i] == loc[i].lower()):
                loc[i] = keydict[loc[i]]
            else:
                loc[i] = U_keydict[loc[i]]

    return "".join(loc)

def k_encrypt(message):
    alphabet = list("abcdefghijklmnopqrstuvwxyz")
    U_alphabet = list("abcdefghijklmnopqrstuvwxyz".upper())
    keys = list("zaqxswcdevfrbgtnhymjukilop")
    U_keys = list("zaqxswcdevfrbgtnhymjukilop".upper())

    keydict = {}
    for i in range(len(alphabet)):
        keydict[alphabet[i]] = keys[i]

    U_keydict = {}
    for i in range(len(U_alphabet)):
        U_keydict[U_alphabet[i]] = U_keys[i]

    loc = list(message)
    for i in range(len(loc)):
        if alphabet.__contains__(loc[i]):
            if (loc[i] == loc[i].lower()):
                loc[i] = keydict[loc[i]]
            else:
                loc[i] = U_keydict[loc[i]]

    return "".join(loc)

mes = "\
    First Question~Who am I?~puzzles/images/equation.jpeg~true~~fibonacci*\
    Riddle~As a stone inside a tree, I'll help your words outlive thee._But if you push me as I stand, the more I move the less I am._ _What am I?~null~false~~a pencil_pencil*\
    Aptitude test~ What is the next number in this sequence?_0 1 3 3 8 21 ?~null~true~~165*\
    Riddle~What is better than god, worse than the devil,_dead people eat it, but if you eat it you die?~null~false~~nothing*\
     ~odgovor je jednostavan~null~true~~simple*\
    Personal Dream Riddle (Easy)~_A pale shadow on dark water,_You'll see me when sight is lost,_A half of me is all you'll see and even that may differ._ _What am I?~null~true~a dream~the moon_moon*\
    Personal Riddle (Very Difficult)~_I am given in times when a guiding hand is needed,_You will not need me if you have me,_but when you need me, with a stranger I will be._ _What am I?~null~false~~directions_a direction_direction*\
    Squares~How many squares are there in this picture?~puzzles/images/squares.jpeg~false~~40_41*\
    Personal Riddle (Difficult)~I am the great one-eyed beast that destroys all in my path,_A demon with no form but a body of wrath,_I am born by the elements and come from the sea,_Through nature I make chaos that spreads across leagues._What am I?~null~true~~hurricane_a hurricane_cyclone_a cyclone_typhoon_a typhoon_hurricanes_cyclones_typhoons*\
    Words~How many meanings does the word 'One' have?~null~false~~3*\
    Words~How many meanings does the word 'Three' have?~null~true~~one*\
    Riddle~Keep me to yourself and I will be held, share me with few and I can be kept, give me to many and I will be broken_What am I?~null~false~~a secret_secrets_secret*\
    Brain teaser~Which numbers are on the keyboard?~null~false~~all of them_all_infinite_infinity_every one_every number_all numbers_all of the numbers*\
    Trick~What does one do when they are coming into a room, or coming back to a room~null~false~~*\
    Riddle~What grows when it eats, but dies when it drinks?~null~false~~a fire_fire_a flame_flames_teenagers*\
    Riddle~I have a head, a tail, and nothing more._What am I?~null~false~~a coin_coin_coins*\
    What am I?~051409071301~null~false~~enigma*\
    A Gollum's folly~What have I got in my pocket?~null~true~/u/SnakeSender~a ring_the ring_ring_the one ring to rule them all*\
    Personal Riddle (Difficult)~Some say you'll see it soon, others say it will never come._All will speak of it, with hopes or in fear._It will kill every being and bring every child to life._It will always exist and never be touched, for it's nature is mystery and it's power is much._What is it?~null~true~~tomorrow_the next day_the future_future*\
    Geometry~Which triangle with the given side lengths has a smaller surface?_A: 300, 400, 700_B: 100, 200, 300~null~true~~neither_both_a and b_none_ab*\
    Brain teaser~Some months have 30 days, some have 31 days._How many months have 28 days?~null~false~~12_all_all of them_every one_every month_all months_all of the months*\
    Riddle~Many have heard me,_but nobody has seen me,_and I will not speak back until spoken to._What am I?~null~false~~an echo_echo_echoes*\
    Story~THE Tribe leader Ignited his enemy's clothes as his Tribe Listened to the sound_of their prisonEr's pleading, without mercy, without remorse._In this tale will you find the answer.~null~false~~story*\
    Tricky History~What is the 6th name given to the Lady of the Lake?~null~true~~nivian*\
    Personal Riddle (Medium)~I have eyes but do not see,_A mouth but can not speak,_Hands that will not hold,_Legs that will not run,_And a heart that can not feel._What am I?~null~false~~a corpse_corpse_a dead man_a dead person_the dead_a dead body_dead body_a body_body*\
    Deep thoughts~101010_what am I?~null~true~~42_the answer to life, the universe, and everything_the answer to the ultimate question of life, the universe, and everything*\
    Riddle~A natural state, I'm sought by all._Go without me, and you shall fall._You do me when you spend,_and use me when you eat to no end._What am I?~null~false~~balance*\
    Vibrating polearm~What is he that builds stronger than either the mason, the shipwright, or the carpenter?~null~true~~the gravedigger_a gravedigger_gravedigger_a grave digger_the grave digger_grave digger*\
    Riddle~Who makes it does not want it,_who buys it does not need it,_and who needs it does not know it._What is it?~null~false~Meghan Won~a coffin_coffin_coffins*\
    Personal Riddle (Very Easy)~I have many faces and many figures,_so you will never guess what my collection uncovers._While regular I may be, I can rob you with glee_for my true strength lies in the gamble_What am i?~null~false~~cards_a card_card_a deck of cards_deck of cards*\
    Final question~This thing all things devours:_Birds, beasts, trees, flowers;_Gnaws iron, bites steel;_Grinds hard stones to meal;_Slays king, ruins town,_And beats high mountain down._ _What as it?~null~false~~time\
"

lines = mes.split("*")
contentsLists = [line.split("~") for line in lines]
for line in contentsLists:
    l = line[5].split("_")
    for i in range(len(l)):
        tmp = l[i].encode('utf-8')
        l[i] = hashlib.md5(tmp).hexdigest()
    line[5] = '_'.join(l)
contentsLists = ['~'.join(line) for line in contentsLists]
contentsLists = '*'.join(contentsLists)


met = encrypt(contentsLists)
print(met)
f = open("w.txt", "w+")
f.write(met)
f.close()