$path = "sdk/pom.xml"
[Reflection.Assembly]::LoadWithPartialName("System.Xml.Linq")
$doc = [System.Xml.Linq.XElement]::Load($path)
$ns = $doc.GetDefaultnamespace()
$configrationElement = [System.Xml.Linq.XElement]::New(
    $ns + "configration",
    [System.Xml.Linq.XElement]::New(
    $ns + "gpgArguments",
    [System.Xml.Linq.XElement]::New($ns + "arg", "--pinentry-mode"),
    [System.Xml.Linq.XElement]::New($ns + "arg", "loopback")
    )
)
$doc.Descendants($ns + "plugin").Where({$_.Element($ns + "artifactId").Value -eq "maven-gpg-plugin"}).Descendants($ns + "execution").Add($configrationElement)
$doc.save($path)
