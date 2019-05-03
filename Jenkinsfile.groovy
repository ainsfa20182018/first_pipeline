node{
    stage("Pull Repo"){
        git 'git@github.com:daudmu21/cool_website.git'
    }
    stage("Webserver Install"){
        sh "ssh ec2-user@${env}  sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html  ec2-user@${env}:/tmp"
    }
    stage("Move Index"){
        sh 'ssh ec2-user@${env}   "sudo mv /tmp/index.html /var/www/html/index.html"'
    }
    stage("Start HTTPD"){
        sh "ssh ec2-user@${env}   sudo systemctl restart httpd"
    }
}