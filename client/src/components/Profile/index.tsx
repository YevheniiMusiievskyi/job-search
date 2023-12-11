import React, {useEffect, useState} from "react";
import {IProfile, IUserProfilePostRequest} from "../../models/userProfile";
import UserProfileInput from "../UserProfileInput";
import {Button, Form, Input} from "semantic-ui-react";
import SkillsInput from "../SkillsInput";
import {mapSkillToTag, mapTagToSkillPostRequest} from "../../helpers/mappers/skillsMapper";
import {ISkill, ITag} from "../../models/skills";
import {getOrEmpty} from "../../helpers/utils";

interface IProfileProps {
    profile: IProfile | null;
    existingSkills: ISkill[];

    updateProfile(userProfile: IUserProfilePostRequest): void;
}

const Profile: React.FC<IProfileProps> = ({profile, existingSkills, updateProfile}) => {

    const [title, setTitle] = useState(getOrEmpty(profile?.title))
    const [description, setDescription] = useState(getOrEmpty(profile?.description))
    const [tags, setTags] = useState(profile ? profile.skills.map(mapSkillToTag) : [])

    useEffect(() => {
        setTitle(getOrEmpty(profile?.title));
        setDescription(getOrEmpty(profile?.description))
        setTags(profile ? profile.skills.map(mapSkillToTag) : [])
    }, [profile])

    const handleSkillAddition = (tag: ITag) => {
        setTags([...tags, tag]);
    };

    const handleDelete = i => {
        setTags(tags.filter((tag, index) => index !== i));
    };

    const handleSkillDrag = (tag, currPos, newPos) => {
        const newTags = tags.slice();

        newTags.splice(currPos, 1);
        newTags.splice(newPos, 0, tag);

        // re-render
        setTags(newTags);
    };

    const handleSave = () => {
        const mappedSkills = tags.map(mapTagToSkillPostRequest)

        const userProfileRequest: IUserProfilePostRequest = {
            title,
            description,
            skills: mappedSkills
        }

        updateProfile(userProfileRequest)
    }

    return (
        <>
            <UserProfileInput rowName="Title">
                <Input
                    name="title" value={title} placeholder='Title'
                    onChange={ev => setTitle((ev.target as HTMLInputElement).value)}/>
            </UserProfileInput>
            <UserProfileInput rowName="Work experience">
                <Form>
                    <Form.TextArea
                        name="description" placeholder='Description' rows={5}
                        value={description}
                        onChange={ev => setDescription((ev.target as HTMLTextAreaElement).value)}/>

                </Form>
            </UserProfileInput>
            <UserProfileInput rowName="Skills">
                <SkillsInput
                    tags={tags}
                    suggestions={existingSkills ? existingSkills.map(mapSkillToTag) : []}
                    handleSkillAddition={handleSkillAddition}
                    handleSkillDelete={handleDelete}
                    handleSkillDrag={handleSkillDrag}
                    readonly={false}
                />
            </UserProfileInput>
            <Button onClick={handleSave} primary>Save</Button>
        </>
    )
}

export default Profile