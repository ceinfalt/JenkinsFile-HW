
def projectName = "hybrisHomework"

node (slave) {
    stage{'Prepare Environment'

    }

     stage{'Build'

    }

     stage{'Test'
        echo "Not testing yet"
    }

     stage{'Deploy'

    }
}
